## 1、禁用swap
### 1.1、临时禁用
````
swapoff -a
````

### 1.2、永久禁用swap
注释swap一行
 ````
 vim /etc/fstab
 ````

## 2、关闭防火墙
````
systemctl stop firewalld

systemctl disable firewalld
````


## 3、关闭selinux
````
sed -i 's/enforcing/disable/' /etc/selinux/config

setenforce 0
````

## 4、添加主机名与ip对应关系
````
vim /etc/hosts

192.0.0.171 k8s-master
192.0.0.141 k8s-node1

````

## 5、将桥接的IPv4流量传递到iptables的链
````
cat > /etc/sysctl.d/k8s.conf <<EOF
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
````
````
sysctl --system
````
## 6、所有节点安装Docker/kubeadm/kubelet/kubectl
k8s默认CRI（容器运行时）为docker

### 6.1、Docker安装
````
yum install -y yum-utils   device-mapper-persistent-data   lvm2

yum-config-manager     --add-repo     https://download.docker.com/linux/centos/docker-ce.repo

yum install docker-ce-18.09.9 docker-ce-cli-18.09.9 containerd.io -y

systemctl start docker

systemctl enable docker

docker --version # 指定18.09.9版本
````

### 6.2、添加阿里云kubernetesYUM软件源
````
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
````

### 6.3、安装
````
yum install -y kubelet-1.16.4 kubeadm-1.16.4 kubectl-1.16.4

systemctl enable kubelet && systemctl start kubelet
````


## 7、部署k8s Master

### 7.1、重置master（看自己需要，如果之前部署过）
````
kubeadm reset

rm -rf $HOME/.kube/config
````

### 7.2、部署master
````
kubeadm init --kubernetes-version=1.16.4  \
--apiserver-advertise-address=192.0.0.171   \
--image-repository registry.aliyuncs.com/google_containers  \
--service-cidr=10.1.0.0/16   \
--pod-network-cidr=10.244.0.0/16
````

#### 7.2.1、记录生成的最后部分内容
````
kubeadm join 192.0.0.171:6443 --token qkgwxh.2dkyy2b699a00rdy \
    --discovery-token-ca-cert-hash sha256:e73abc5afc2f0c532b65a9e1c5cf80d69f6e6553d4660a6a457ff502b7d9d8c8
````

#### 7.2.2、创建kubectl
````
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
````

#### 7.2.3、kubectl可以自动补充
````
source <(kubectl completion bash)
````

#### 7.2.4、安装网络插件flannel
node节点都需要拉取flannel镜像

````
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/2140ac876ef134e0ed5af15c65e414cf26827915/Documentation/kube-flannel.yml
````

#### 7.2.5、查看pod
````
kubectl get pods  -n kube-system
````

## 8、部署k8s node
````
kubeadm join 192.0.0.171:6443 --token qkgwxh.2dkyy2b699a00rdy \
    --discovery-token-ca-cert-hash sha256:e73abc5afc2f0c532b65a9e1c5cf80d69f6e6553d4660a6a457ff502b7d9d8c8
````
出现 `Run 'kubectl get nodes' on the control-plane to see this node join the cluster.`
加入成功，master节点查看nodes
````
kubectl get nodes
````

## 9、测试集群
````
kubectl create deployment nginx --image=nginx

kubectl expose deployment nginx --port=80 --type=NodePort

kubectl get pod,svc
````

等待容器启动完成，使用nodeIP+port，进行访问

## 10、安装kubernetes-dashboard

### 10.1、下载ymal
````
wget https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-bate8/aio/deploy/recommended.yaml
````

### 10.2、配置yaml（有梯子可以不用）
````
sed -i 's/kubernetesui/registry.cn-hangzhou.aliyuncs.com\/loong576/g' recommended.yaml
````
### 10.3、配置NodePort
````
sed -i '/targetPort: 8443/a\ \ \ \ \ \ nodePort: 30002\n\ \ type: NodePort' recommended.yaml
````

### 10.4、部署dashboard
````
kubectl apply -f recommended.yaml

kubectl get pods -n kubernetes-dashboard

kubectl get pods,svc -n kubernetes-dashboard
````

如果想删除重新配置
````
kubectl delete -f recommended.yaml
````

### 10.5、登陆dashboard
访问地址，注意是`https`，使用火狐浏览器

````
https://<nodeIP><nodePort>
````

### 10.6、创建管理员
````
kubectl create serviceaccount dashboard-admin -n kube-system

kubectl create clusterrolebinding dashboard-admin --clusterrole=cluster-admin --serviceaccount=kube-system:dashboard-admin
````
### 10.7、获取token
````
kubectl describe secrets -n kube-system $(kubectl -n kube-system get secret | grep dashboard-admin | awk '{print $1}')
````

### 10.8、登陆输入token即可

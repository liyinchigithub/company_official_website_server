# 使用CentOS作为基础镜像
FROM centos:7

# 更换为阿里云的yum源
RUN sed -i 's|^mirrorlist=|#mirrorlist=|g' /etc/yum.repos.d/CentOS-Base.repo && \
    sed -i 's|^#baseurl=http://mirror.centos.org|baseurl=https://mirrors.aliyun.com|g' /etc/yum.repos.d/CentOS-Base.repo

# 安装必要的工具和软件，包括curl
RUN yum update -y && \
    yum install -y java-1.8.0-openjdk-devel mysql-server nginx curl && \
    yum clean all

# 安装 Maven 3.8.6
RUN curl -L -o /tmp/apache-maven-3.8.6-bin.tar.gz https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz && \
    tar -xzf /tmp/apache-maven-3.8.6-bin.tar.gz -C /opt/ && \
    ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/bin/mvn

# 设置工作目录
WORKDIR /app

# 将项目文件复制到容器中
COPY . .

# 复制Nginx配置文件
COPY nginx.conf /etc/nginx/nginx.conf

# 添加 Maven 配置文件
COPY settings.xml /root/.m2/settings.xml

# 编译项目
RUN mvn clean package

# 暴露服务端口
EXPOSE 8080

# 启动MySQL和Nginx
CMD service mysqld start && \
    service nginx start && \
    java -jar target/your-app.jar
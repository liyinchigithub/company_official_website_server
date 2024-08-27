# 使用CentOS作为基础镜像
FROM centos:latest

# 安装必要的工具和软件
RUN yum update -y && \
    yum install -y java-1.8.0-openjdk-devel mysql-server nginx && \
    yum clean all

# 设置工作目录
WORKDIR /app

# 将项目文件复制到容器中
COPY . .

# 复制Nginx配置文件
COPY nginx.conf /etc/nginx/nginx.conf

# 编译项目（假设使用Maven构建）
RUN mvn clean package

# 暴露服务端口
EXPOSE 8080

# 启动MySQL和Nginx
CMD service mysqld start && \
    service nginx start && \
    java -jar target/your-app.jar
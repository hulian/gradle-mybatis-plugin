FROM openjdk:11
WORKDIR /home
ENV PATH=/usr/local/openjdk-11/bin:$PATH
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY libs ./libs
COPY app.jar app.jar
COPY entrypoint.sh entrypoint.sh
ENTRYPOINT ["sh", "./entrypoint.sh"]

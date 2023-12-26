# 자바 런타임이 포함된 베이스 이미지로 시작
FROM openjdk:8-jdk-alpine

# /tmp를 가리키는 볼륨 추가
VOLUME /tmp

# 이 컨테이너 밖의 세계에 8080 포트를 사용할 수 있게 함
EXPOSE 8080

# 애플리케이션의 jar 파일
ARG JAR_FILE=build/libs/webDemo-BackSpring-admin-api-2.0.0-SNAPSHOT.jar

# 애플리케이션의 jar를 컨테이너에 추가
ADD ${JAR_FILE} app.jar

# jar 파일 실행 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

FROM maven:3.6.3-openjdk-17

LABEL maintaner="Bruno Leonardo Monteiro <brunoleomont@gmail.com>"

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install -DskipTests

CMD mvn spring-boot:run
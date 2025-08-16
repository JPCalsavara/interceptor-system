# Estágio 1: Build da Aplicação com Maven e JDK 21
# A imagem base foi atualizada para uma que suporta Java 21.
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Define o locale para garantir a codificação UTF-8.
ENV LANG C.UTF-8

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia os arquivos do Maven Wrapper e o pom.xml primeiro para aproveitar o cache.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa todas as dependências do projeto.
RUN ./mvnw dependency:go-offline

# Copia o código-fonte da sua aplicação.
COPY src ./src

# Executa o build do projeto, gerando o arquivo .jar.
RUN ./mvnw package -DskipTests


# Estágio 2: Imagem Final de Execução
# Usamos uma imagem JRE (Java Runtime Environment) baseada em Alpine, que é muito leve.
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho.
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio 'builder' para a imagem final.
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot.
EXPOSE 8080

# Comando para iniciar a aplicação quando o container for executado.
ENTRYPOINT ["java", "-jar", "app.jar"]

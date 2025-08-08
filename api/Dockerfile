# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem que já contém o Maven e o JDK 17 para compilar o projeto.
FROM maven:3.8.5-openjdk-17 AS builder

# Define o locale para garantir a codificação UTF-8, prevenindo erros de 'MalformedInputException'.
ENV LANG C.UTF-8

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia os arquivos do Maven Wrapper e o pom.xml primeiro.
# Isso aproveita o cache do Docker. Se o pom.xml não mudar, as dependências não serão baixadas novamente.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa todas as dependências do projeto.
RUN ./mvnw dependency:go-offline

# Copia o código-fonte da sua aplicação.
COPY src ./src

# Executa o build do projeto, gerando o arquivo .jar.
# -DskipTests pula a execução dos testes para agilizar o build.
RUN ./mvnw package -DskipTests


# Estágio 2: Imagem Final de Execução
# Usamos uma imagem JRE (Java Runtime Environment) baseada em Alpine, que é muito leve.
FROM eclipse-temurin:17-jre-alpine

# Define o diretório de trabalho.
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio 'builder' para a imagem final.
# Renomeia para 'app.jar' para facilitar a execução.
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot.
EXPOSE 8080

# Comando para iniciar a aplicação quando o container for executado.
ENTRYPOINT ["java", "-jar", "app.jar"]

# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem oficial do Maven com o Java 21 (Temurin)
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Define o diretório de trabalho dentro do contentor
WORKDIR /app

# Copia primeiro o ficheiro pom.xml para aproveitar o cache do Docker
COPY pom.xml ./

# Descarrega todas as dependências do projeto
RUN mvn dependency:go-offline

# Copia o resto do código-fonte da sua aplicação
COPY src ./src

# Executa o build do projeto com o Maven, saltando os testes
RUN mvn package -DskipTests


# Estágio 2: Execução da Aplicação
# Usamos uma imagem JRE (Java Runtime Environment), que é muito mais leve
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o ficheiro .jar gerado no estágio de build para o nosso contentor final
COPY --from=builder /app/target/CostureiraPlus-0.0.1-SNAPSHOT.jar ./app.jar

# Expõe a porta que a Render irá usar. A Render define a porta através de uma variável de ambiente.
# Embora a sua app use a 8080, a Render pode mapeá-la para outra porta. EXPOSE é mais uma documentação.
EXPOSE 8080

# Comando para iniciar a aplicação quando o contentor for executado
ENTRYPOINT ["java", "-jar", "app.jar"]
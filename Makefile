# Makefile for the Order Service (Maven)

# Project name
PROJECT_NAME = order

# Build directory
BUILD_DIR = target

# Source directory
SRC_DIR = src/main/java

# Main class
MAIN_CLASS = com.nextdigital.app.order.OrderApplication # Change this if your main class is different

# Compiler and JVM
JAVAC = javac
JAVA = java

# Maven command
MAVEN = mvn

# Default target
all: compile test jar

# Installation target (optional, for installing the jar to a local Maven repo)
install:
	$(MAVEN) install

run: ## Run the application using Maven
	$(MAVEN) spring-boot:run

compile: ## Compile Java source code using Maven
	@echo "Compiling Java source code with Maven..."
	$(MAVEN) compile

jar: compile ## Package the application into a JAR file using Maven
	@echo "Creating JAR archive with Maven..."
	$(MAVEN) package

test: compile ## Run tests using Maven
	@echo "Running tests with Maven..."
	$(MAVEN) test

clean: ## Clean the build directory using Mave
	@echo "Cleaning build directory with Maven..."
	$(MAVEN) clean

# Rebuild the project using Maven
rebuild: clean all

.PHONY: all install run compile test clean rebuild jar

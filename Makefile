clean:
	./gradlew clean
build:
	./gradlew build
test:
	export NLP=localhost:5000
	sudo docker-compose up nlp -d
	./gradlew test

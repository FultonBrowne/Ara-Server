clean:
	./gradlew clean
build:
	./gradlew build
test:
	export NLP=localhost:5000
	docker-compose up nlp -d
	./gradlew test

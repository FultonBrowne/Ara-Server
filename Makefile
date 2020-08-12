clean:
	./gradlew clean
build:
	-cowsay ara is building
	./gradlew build

test:
	export NLP=localhost:5000
	sudo docker-compose up nlp -d
	./gradlew test
	sudo docker compose down

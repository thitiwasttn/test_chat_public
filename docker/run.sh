docker build -t chatimage .

docker stop chat

docker rm chat

docker run -it -d --name chat -p9997:8081 chatimage

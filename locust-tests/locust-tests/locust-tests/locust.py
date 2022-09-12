
from locust import HttpUser, SequentialTaskSet, between, task
import json


class User(HttpUser):
    @task
    class SequenceOfTasks(SequentialTaskSet):
        wait_time = between(1, 5)

        @task
        def ping(self):
            self.client.get("/ping")

        @task
        def post(self):
            self.client.post("/", json=
            {
                 "url": "https://www.Arseal.com"
            }
            )


#   locust --host=http://localhost:8085 --locustfile locust.py


from locust import HttpUser, SequentialTaskSet, between, task
import json
import csv


class User(HttpUser):

    global data
    # if (data == None):
    #     with open('top500domains.csv', 'r') as l:
    #         reader = csv.reader(l)
    #         data = list(reader)
    with open('top500domains.csv', 'r') as l:
        reader = csv.reader(l)
        data = list(reader)

    @task
    class SequenceOfTasks(SequentialTaskSet):
        wait_time = between(1, 5)

        @task
        def ping(self):
            self.client.get("/ping")

        @task
        def post(self):
            if len(data) > 0:
                self.my_value = data.pop()[0]
            self.client.post("/", json=
            {
                "url": "https://" + self.my_value
            }
                             )

#   locust --host=http://localhost:8085 --locustfile locust.py

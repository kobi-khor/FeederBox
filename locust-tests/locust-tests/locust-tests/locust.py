import time
from locust import HttpUser, task, between

class QuickstartUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def hello_world(self):
        self.client.get("/ping")

# class Test_1(TaskSet):
#     @task(1)
#     def users(self):
#         response = self.client.post("/posts", json=
#         {
#         "url": "www.Google.com"
#         }
#         )
#         # json_var = response.json()
#         # request_id = json_var['title']
#         # print 'Post title is ' + request_id


#  /Users/kobihorshid/Desktop/FeederBox/locust-tests/locust-tests

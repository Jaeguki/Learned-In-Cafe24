from django.http import HttpResponse
import requests
from django.template import loader

url = 'http://localhost'
port = '8000'


def index(request, mall_link):
    if request.method == 'GET':
        request_url = url + ':' + port + '/api/v1/malls/' + mall_link
        response = requests.get(request_url).json()
        print(response)
        template = loader.get_template('mall/index.html')
        return HttpResponse(template.render({'mall': response}, request))

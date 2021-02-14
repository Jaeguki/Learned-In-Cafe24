from django.http import HttpResponse
import requests
from django.template import loader

url = 'http://localhost'
port = '8000'


def index(request):
    if request.method == 'GET':
        template = loader.get_template('main/index.html')
        return HttpResponse(template.render({}, request))


def mall_list(request):
    if request.method == 'GET':
        request_url = url + ':' + port + '/api/v1/malls'
        response = requests.get(request_url).json()
        template = loader.get_template('main/mall-list.html')
        return HttpResponse(template.render({'malls': response}, request))

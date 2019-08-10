import requests
from django.http import HttpResponse

# Create your views here.
from django.template import loader

url = 'http://localhost'
port = '8000'


def member_list(request):
    if request.method == 'GET':
        request_url = url + ':' + port + '/api/v1/members'
        response = requests.get(request_url).json()
        template = loader.get_template('admin/member-list.html')
        return HttpResponse(template.render({'members': response}, request))


def non_member_list(request):
    if request.method == 'GET':
        request_url = url + ':' + port + '/api/v1/nonMembers'
        response = requests.get(request_url).json()
        template = loader.get_template('admin/non-member-list.html')
        return HttpResponse(template.render({'nonMembers': response}, request))

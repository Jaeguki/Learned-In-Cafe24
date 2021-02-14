import requests
from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render

# Create your views here.
from django.template import loader

url = 'http://localhost'
port = '8000'


def login(request):
    if request.method == 'GET':
        template = loader.get_template('member/login.html')
        return HttpResponse(template.render({}, request))
    if request.method == 'POST':
        request_url = url + ':' + port + '/api/v1/members/login'
        response = requests.post(request_url, {'id': request.POST['id'], 'password': request.POST['password']}).json()
        if response['result'] == 'fail':
            return HttpResponseRedirect('/member/login?result=fail')
        authuser = response['data']
        request.session['authuser'] = authuser
        print(authuser)
        # response.set_cookie('id', request.session['authuser']['id'])
        # print(request.COOKIES)

        return HttpResponseRedirect('/')


def join(request):
    if request.method == 'GET':
        template = loader.get_template('member/join.html')
        return HttpResponse(template.render({}, request))
    if request.method == 'POST':
        request_url = url + ':' + port + '/api/v1/members/join'
        response = requests.post(request_url, {'id': request.POST['id'], 'password': request.POST['password']}).json()
        if response['result'] == 'fail':
            return HttpResponseRedirect('/member/login?result=fail')
        authuser = response['data']
        request.session['authuser'] = authuser
        print(authuser)
        # response.set_cookie('id', request.session['authuser']['id'])
        # print(request.COOKIES)

        return HttpResponseRedirect('/')


def logout(request):
    del request.session['authuser']
    return HttpResponseRedirect('/')

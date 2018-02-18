from django.http import HttpResponse 
"""
def index(request) : 
    return HttpResponse("server is running")
    """
from rest_framework.renderers import JSONRenderer
from smart.serializers import WindowSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from smart.models import Windowmodel

class JSONResponse(HttpResponse):
    def __init__(self, data, **kwargs):

        content = JSONRenderer().render(data, 'application/json; indent=4')

        kwargs['content_type'] = 'application/json'

        super(JSONResponse, self).__init__(content, **kwargs)
@api_view(['GET','POST'])
def Windowlist(request, format=None):

    if request.method == 'GET':

        packages = Windowmodel.objects.all()

        serializer = WindowSerializer(packages, many=True)

        return JSONResponse(serializer.data)

"""
WSGI config for window project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/2.0/howto/deployment/wsgi/
"""

"""import os

from django.core.wsgi import get_wsgi_application

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "window.settings")

application = get_wsgi_application()
"""

"""import os
import sys
sys.path.append('/opt/project/web')
os.environ['DJANGO_SETTINGS_MODULE'] = 'window.settings'

import django.core.handlers.wsgi
application = django.core.handlers.wsgi.WSGIHandler()"""
import os
#import sys
from django.core.wsgi import get_wsgi_application
#sys.path.append('c:/git_yychani/window')
os.environ['DJANGO_SETTINGS_MODULE']='window.settings'
application=get_wsgi_application()
import django
django.setup()
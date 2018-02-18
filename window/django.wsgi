import os
import sys
sys.path.append('/opt/project/web')
os.environ['DJANGO_SETTINGS_MODULE'] = 'window.settings'

import django.core.handlers.wsgi
application = django.core.handlers.wsgi.WSGIHandler()
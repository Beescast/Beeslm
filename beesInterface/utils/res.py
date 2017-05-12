import time
from django.http import JsonResponse
from utils.validateUtils import ValidateUtils

class Res:
	OK = 200
	Redirect = 302
	Verify = 403
	Error = 500

	def __init__(self):
		self.code = Res.OK
		self.messages = None
		self.data = {'time': int(time.time()*1000)}

	def addMessage(self, id, message, cdata):
		if not ValidateUtils.isNull(message):
			if cdata:
				message = '<![CDATA[ ' + message + ' ]]>'
			self.messages = {'id':id, 'message':message}

	def addSuccess(self, message):
		self.addMessage('doAction', message, False)

	def setRedirect(self, url):
		self.code = Res.Redirect
		self.addMessage('returnUrl', url, False)

	def setVerify(self):
		self.code = Res.Verify

	def addError(self, errorMessage):
		self.addMessage('doAction', errorMessage, False)
		self.code = Res.Error

	def addData(self, key, value):
		self.data[key] = value

	def setResponse(self, value):
		self.addData('response', value)

	def addResponse(self, key, value):
		response = self.data.get('response', {})
		response[key] = value
		self.data['response'] = response

	def toJavaScript(self, string):
		None

	def toJson(self):
		None

	def toXml(self):
		None

	def toJs(self):
		None

	def toView(self, formatType, viewName, callback, response):
		#response.setdefault("Access-Control-Allow-Origin", "*")
		if formatType.lower() == 'json':
			res = {}
			res['code'] = self.code
			res['messages'] = self.messages
			res['data'] = self.data
			jr = JsonResponse(res, json_dumps_params={'ensure_ascii':False})
			#jr.setdefault("Access-Control-Allow-Origin", "*")
			return jr


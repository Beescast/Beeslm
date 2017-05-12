#coding=utf-8

cacheDict = {}
expireDict = {}

class CacheService:
	def __init__(self):
		self.csdict = {}

	@staticmethod
	def initService():
		global  cacheDict
		global expireDict
		cacheDict = {}
		expireDict = {}

	@staticmethod
	def getAttribute(key):
		return cacheDict.get(key)

	@staticmethod
	def setAttribute(key, obj):
		cacheDict[key] = obj

	# 向集合中增加一条记录, 如果这个值已存在，这个值对应的权重将被置为新的权重
	# @ param String key
	# @ param double score 权重
	# @ param String member 要加入的值，
	# @ return 状态码 1 成功，0 已存在member的值
	def zadd(self, key, score, member):
		pass

	 # 获取指定值在集合中的位置，集合排序从低到高
	 # @see zrank
	 # @param String key
	 # @param String member
	 # @return long 位置
	def zrevrank(self, key, member):
		pass

	@staticmethod
	def exists(key):
		if key in expireDict:
			return True
		else:
			return False

	@staticmethod
	def setExpire(key, expire): # expire的单位是秒
		expireDict[key] = expire

	@staticmethod
	def removeAttribute(key):
		cacheDict.pop(key)
		expireDict.pop(key)

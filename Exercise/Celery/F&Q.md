

# django_celery_beat任务参数中中文名称的问题

任务参数有一个中文的参数,无法存到`django_celery_beat_periodictask`这个表中。

![](https://ws1.sinaimg.cn/large/006tNbRwgy1fxhrj819z6j31h40p2qry.jpg)

找到django-celery-beat这个issue:[The name field of PeriodicTask does not supports Chinese characters](https://github.com/celery/django-celery-beat/issues/10)

这里面没有提到解决办法,只能入库用unicode编码，读取的时候再将unicode转成utf-8。


.encode('string_escape')

##### һ��redis
- ������������
	- 1��Ĭ�ϵ����ݿ�16����Ĭ��ȡ��0������select 1�л����ݿ�
	- 2��ttl���鿴ʣ��ʱ�䣬����Ϊ��λ��-1��ʾ������ڣ�-2��ʾû�����ֵ���߹����ˡ�
	- 3��type���鿴keyֵ���ͣ�type a
	- 4��nx��β����
	- 5��jedis��redis�İ汾��ñ���һ��
	- 6��redis�����ռ������":",
	- 7��redis-cli������־���monitor
- redis�ֲ�ʽ��
	- 1��setnx��getset��������ԭ����
	- 2��redis�ֲ�ʽ���setnx��getset��expire��del����
	- 3���ֲ�ʽ����ͼ��
		- 1������˼·��
		
![avatar](https://github.com/szfst/learnNote/blob/master/jgyj/redis/redis-1.jpg?raw=true)

	private void closeOrder(String lockName){
        RedisShardedPoolUtil.expire(lockName,5);//��Ч��50�룬��ֹ����
        log.info("��ȡ{},ThreadName:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,Thread.currentThread().getName());
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour","2"));
        iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        log.info("�ͷ�{},ThreadName:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,Thread.currentThread().getName());
        log.info("===============================");
    }
    //
	log.info("�رն�����ʱ��������");
	long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout","5000"));
	Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,String.valueOf(System.currentTimeMillis()+lockTimeout));
        if(setnxResult != null && setnxResult.intValue() == 1){
            //�������ֵ��1���������óɹ�����ȡ��
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }else{
            log.info("û�л�÷ֲ�ʽ��:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }
        log.info("�رն�����ʱ�������");
        
- 2���Ż�˼·��
    - ΪʲôҪ�ô˷�����˫�ط�ֹ��������ֹ��δ����expire��ʱ��رճ������޷��ͷ�

![avatar](https://github.com/szfst/learnNote/blob/master/jgyj/redis/redis-2.jpg?raw=true)

	    log.info("�رն�����ʱ��������");
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout","5000"));
        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,String.valueOf(System.currentTimeMillis()+lockTimeout));
        if(setnxResult != null && setnxResult.intValue() == 1){
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }else{
            //δ��ȡ�����������жϣ��ж�ʱ��������Ƿ�������ò���ȡ����
            String lockValueStr = RedisShardedPoolUtil.get(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            if(lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)){
                String getSetResult = RedisShardedPoolUtil.getSet(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,String.valueOf(System.currentTimeMillis()+lockTimeout));
                //�ٴ��õ�ǰʱ���getset��
                //���ظ�����key�ľ�ֵ��->��ֵ�жϣ��Ƿ���Ի�ȡ��
                //��keyû�о�ֵʱ����key������ʱ������nil ->��ȡ��
                //��������set��һ���µ�valueֵ����ȡ�ɵ�ֵ��
                if(getSetResult == null || (getSetResult != null && StringUtils.equals(lockValueStr,getSetResult))){
                    //������ȡ����
                    closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }else{
                    log.info("û�л�ȡ���ֲ�ʽ��:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }
            }else{
                log.info("û�л�ȡ���ֲ�ʽ��:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            }
        }
        log.info("�رն�����ʱ�������");
- 3��javaֱ����redission</br>
	redission wait_time����ȡ���ĵȴ�ʱ�䣬���ͳһ����Ϊ0����ֹ�п�	
```java	
	RLock lock = redissonManager.getRedisson().getLock(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        boolean getLock = false;
        try {
            if(getLock = lock.tryLock(0,50, TimeUnit.SECONDS)){
                log.info("Redisson��ȡ���ֲ�ʽ��:{},ThreadName:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,Thread.currentThread().getName());
                int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour","2"));
//                iOrderService.closeOrder(hour);
            }else{
                log.info("Redissonû�л�ȡ���ֲ�ʽ��:{},ThreadName:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            log.error("Redisson�ֲ�ʽ����ȡ�쳣",e);
        } finally {
            if(!getLock){
                return;
            }
            lock.unlock();
            log.info("Redisson�ֲ�ʽ���ͷ���");
        }
```
- redis����
���������
redis.max.total=20
��������
redis.max.idle=10
��С������
redis.min.idle=2
��jedis���ӳػ�ȡ����ʱ��У�鲢���ؿ��õ�����
redis.test.borrow=true
�����ӷŻ�jedis���ӳ�ʱ��У�鲢���ؿ��õ�����
redis.test.return=false
//���Ӻľ��Ƿ�����
config.setBlockWhenExhausted(true);//���Ӻľ���ʱ���Ƿ�������false���׳��쳣��true����ֱ����ʱ��Ĭ��Ϊtrue��
- pool.close()
	- ����RedisPool.returnBrokenResource(jedis)��           RedisPool.returnResource(jedis)������close������
- �����¼��
	- cookie.setHttpOnly(true);����ͨ���ű���ȡcookie����ֹ��������ֹ���˰�cookies���͵��Լ�����վ���Ƿ���;��
- redis�ֲ�ʽ��
	- �ֲ�ʽ�㷨��consistent hashing��һ����hash�㷨��
	- ԭ������hash������ڵ�
		- �����ʼ��㹫ʽ����1-n/(n+m))*100%��nΪ����������
	- java���룺shardedJedis
- redis�������ã�����slave of������ͬ�����Ӳ���дֻ�ܶ�����д������֮��Ӿ�����д�����ݣ����ǴӲ���д
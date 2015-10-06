#cache-noop-ecm

ECM based components for [cache-noop][1]. The component registers a java.util.Map 
and a java.util.concurrent.ConcurrentMap interfaces as OSGi service 
automatically.

The services can be filtered with the following expression:
`(cache.driver.name=noop)`

The values are available in constants:
 - `org.everit.cache.CacheConstants` (from the cache-api)
 - `org.everit.cache.noop.ecm.NoOpCacheConstants`

[1]: https://github.com/everit-org/cache-noop

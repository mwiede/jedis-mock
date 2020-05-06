[![GitHub release](https://img.shields.io/github/v/tag/mwiede/jedis-mock)](https://github.com/mwiede/jedis-mock/releases/latest)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mwiede/jedis-mock/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mwiede/jedis-mock)
[![Build Status](https://api.travis-ci.org/mwiede/jedis-mock.svg?branch=master)](https://travis-ci.org/mwiede/jedis-mock)

# Jedis-Mock

Redis Mock is a simple in-memory mock of redis for java testing. 
It allows you to test any behaviour dependent on redis without having to deploy an instance of redis

## Quickstart 

Add it as a dependency in Maven as:

```xml
<dependency>
  <groupId>com.github.mwiede</groupId>
  <artifactId>jedis-mock</artifactId>
  <version>0.1.16</version>
</dependency>
```

Create a redis server and bind it to jedis:

```
private static RedisServer server = null;

@Before
public void before() throws IOException {
  server = RedisServer.newRedisServer();  // bind to a random port
  server.start();
}

@Test
public void test() {
  ...
  Jedis jedis = new Jedis(server.getHost(), server.getBindPort());
  ...
}

@After
public void after() {
  server.stop();
  server = null;
}
```

From here test as needed

## Fault tolerance testing

We can make a RedisServer close connection after every several commands. This will cause a connection exception for clients.

```
RedisServer server = RedisServer.newRedisServer();
ServiceOptions options = new ServiceOptions();
options.setCloseSocketAfterSeveralCommands(3);
server.setOptions(options);
server.start();
```

## Supported Operations

All currently supported operations are listed [here](https://github.com/mwiede/jedis-mock/blob/master/src/main/java/com/github/fppt/jedismock/operations/OperationFactory.java)

## Missing Operations

If you get the following error:

```
Unsupported operation {}
```

Please feel free to create an issue requesting the missing operation.


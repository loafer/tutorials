自定义登录页
====
用过CAS的人都知道，CAS-Server做为认证中心需要单独部署。当用户每次登录业务系统时，都会被重定向到CAS-Server的登录页上。但是有时候我们希望登录页能更佳灵活，比如可以嵌入到业务系统的某个页面中的一个面板内。本Demo就演示了这个功能，本工程只是针对客户端的改造，有关CAS-Server端的改造请查看另一个工程。

参考自：https://wiki.jasig.org/display/CAS/Using+CAS+without+the+Login+Screen
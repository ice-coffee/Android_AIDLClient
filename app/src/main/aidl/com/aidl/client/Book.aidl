// BookA.aidl
package com.aidl.client;

/*这个文件的作用是引入了一个序列化对象 Book 供其他的AIDL文件使用; 注意：Book.aidl与Book.java的包名应当是一样的*/
/*parcelable 为小写*/

/*在使用 AS 自动生成同名 aidl 文件时会被拒绝, 这时候可以先写一个别的名字, 然后修改完文件内容, 如下, 后再把名字跟bean类统一*/
parcelable Book;

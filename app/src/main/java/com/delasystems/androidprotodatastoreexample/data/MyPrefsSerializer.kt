package com.delasystems.androidprotodatastoreexample.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.delasystems.androidprotodatastoreexample.MyPrefs
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object MyPrefsSerializer : Serializer<MyPrefs> {
    override val defaultValue: MyPrefs = MyPrefs.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): MyPrefs {
        try {
            return MyPrefs.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: MyPrefs,
        output: OutputStream
    ) = t.writeTo(output)
}


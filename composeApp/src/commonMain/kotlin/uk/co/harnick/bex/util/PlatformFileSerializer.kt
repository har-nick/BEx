package uk.co.harnick.bex.util

import io.github.vinceglb.filekit.PlatformFile
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PlatformFileSerializer : KSerializer<PlatformFile> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("PlatformFileToString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: PlatformFile) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): PlatformFile {
        return PlatformFile(decoder.decodeString())
    }
}

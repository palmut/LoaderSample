package palmut.ru.loadersample

sealed class SampleData
data class SampleDataWithString(val data: String) : SampleData()
data class CachedSampleDataWithString(val data: String) : SampleData()

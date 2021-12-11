package mock

val TestData = listOf(
    TestDomain("Id1", "Text1"),
    TestDomain("Id2", "Text2"),
    TestDomain("Id3", "Text3"),
    TestDomain("Id4", "Text4"),
)

val errorOccurredTestData = listOf(
    TestDomain("Id1", "Text1"),
    TestDomain("Id2", "Text2"),
    TestDomain("Id3", "Text3"),
    TestDomain("Id1", "Text4"),
)

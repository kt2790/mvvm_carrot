package com.example.mvvmcarrot

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvvmcarrot.data.ItemDatabase
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.repository.ItemRepository
import com.example.mvvmcarrot.repository.ItemRepositoryImpl
import com.example.mvvmcarrot.ui.viewmodel.DetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [33])
class ExampleUnitTest {

    private lateinit var application: Context

    @Before
    fun init() {
        application = ApplicationProvider.getApplicationContext()
    }

    @Test

    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun `viewmodel_test 업데이트 카운트 테스트`() = runBlocking {

        val itemList: MutableList<Item> = mutableListOf(
            Item(
                id = 1,
                img = "sample1",
                title = "산진 한달된 선풍기 팝니다",
                content = "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                seller = "대현동",
                price = "1000",
                address = "서울 서대문구 창천동",
                likeCnt = 13,
                chatCnt = 25,
                bookmark = false
            ),
            Item(
                id = 2,
                img = "sample2",
                title = "김치냉장고",
                content = "이사로인해 내놔요",
                seller = "안마담",
                price = "1000",
                address = "서울 서대문구 창천동",
                likeCnt = 13,
                chatCnt = 25,
                bookmark = false
            ),
            Item(
                id = 3,
                img = "sample3",
                title = "샤넬 카드지갑",
                content = "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                seller = "코코유",
                price = "10000",
                address = "수성구 범어동",
                likeCnt = 23,
                chatCnt = 5,
                bookmark = false
            ),
            Item(
                id = 4,
                img = "sample4",
                title = "금고",
                content = "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",
                seller = "Nicole",
                price = "10000",
                address = "해운대구 우제2동",
                likeCnt = 14,
                chatCnt = 17,
                bookmark = false
            ),
            Item(
                id = 5,
                img = "sample5",
                title = "갤럭시Z플립3 팝니다",
                content = "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
                seller = "대현동",
                price = "1000",
                address = "서울 서대문구 창천동",
                likeCnt = 22,
                chatCnt = 9,
                bookmark = false
            ),
            Item(
                id = 6,
                img = "sample6",
                title = "프라다 복조리백",
                content = "까임 오염없고 상태 깨끗합니다\n정품여부모름",
                seller = "미니멀하게",
                price = "50000",
                address = "수원시 영통구 원천동",
                likeCnt = 25,
                chatCnt = 16,
                bookmark = false
            ),
            Item(
                id = 7,
                img = "sample7",
                title = "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                content = "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
                seller = "굿리치",
                price = "150000",
                address = "남구 옥동",
                likeCnt = 142,
                chatCnt = 54,
                bookmark = false
            )

        )


        val mockDatabase = Room.inMemoryDatabaseBuilder(application, ItemDatabase::class.java).build()
        val mockItemDao = mockDatabase.itemDao()
        val repository = ItemRepositoryImpl.getRepository(mockItemDao)

        itemList.forEach {
            repository.addItem(it)
        }

        val viewModel = DetailViewModel(repository)

        val item : Flow<Item> = viewModel.readItem(5)

        /*

         flow {
         */
        /*
        CoroutineScope(Dispatchers.IO).launch {
            item.collect {
                println("likeCnt : ${it.likeCnt}")
            }
        }

        22
        23

        */
        val beforeItem : Item = item.firstOrNull()!!
        val beforeCnt : Int = beforeItem.likeCnt

        viewModel.updateBookmark(beforeItem)

        /*
        flow {
         emit(item)
         emit(item)
         */

        val afterItem : Item = item.firstOrNull()!!
        val afterCnt : Int = afterItem.likeCnt

        CoroutineScope(Dispatchers.IO).launch {
            item.collect {
                println("likeCnt : ${it.likeCnt}")
            }
        }

        assertEquals(beforeCnt + 1, afterCnt)

    }



}
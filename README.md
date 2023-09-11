# 💡 Topic

- **MVVM 패턴을 적용한 당근마켓 클론코딩**

# 📝 Summary

당근 마켓의 기본적인 기능에 해당하는 상품 리스트, 상품 상세 정보, 북마크 기능을 MVVM 패턴을 적용해서 구현하였습니다.

![run](https://github.com/kt2790/mvvm_carrot/assets/138543028/998608a0-8dc4-4784-b535-66a62da7677a){: width="30%", height="30%"}



# ⭐️ Key Function

- 사용자가 등록해둔 물품을 RecyclerView 를 통해 보여줌
- LiveData, Flow 를 활용해 사용자가 물품을 삭제했을 때, DB 변경을 Flow를 통해 실시간으로 감지, ViewModel 에선 Flow 를 LiveData 로 변환하여 가지고 있고, View 단에서는 ViewModel 의 LiveData 를 Observing 하는 방식으로 반응형 UI 제공

# 🛠 Tech Stack

`Kotlin`, `JetPack`, `ViewModel`, `AAC`, `Coroutine`, `Flow`, `LiveData`, `Room DB`

# ⚙️ Architecture

- `MVVM`

# 🤚🏻 Part

- **개인 프로젝트**

# 🤔 Learned

- JetPack **`Room`** 로컬 데이터베이스 사용법을 알게 되었음.
- **MVVM** 패턴을 도입해, 기존의 **MVC**, **MVP** 아키텍처의 문제점을 개선시켜 볼 수 있었음.
- **Coroutine**, **LiveData**, **Flow** 를 사용해보면서, 비동기 프로그래밍에 대한 전반적인 이해를 다질 수 있었음.

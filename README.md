# 📖Easy Text Editor

![GitHub repo size](https://img.shields.io/github/repo-size/JoongSeokD/easy-text-editor)
![GitHub contributors](https://img.shields.io/github/contributors/JoongSeokD/easy-text-editor)
![GitHub forks](https://img.shields.io/github/forks/JoongSeokD/easy-text-editor?style=social)

Easy Text Editor는 CLI환경에서 간단히 텍스트를 추가, 수정, 삭제 하는 프로그램입니다. 📖

명령어는 다음과 같습니다.
```
edit(시작인덱스:숫자, 끝인덱스:숫자, 치환할 문자열:"temp")
redo : 재 실행
undo : 실행 취소
help : 도움말
? : 도움말
exit : 종료
```


## 전제 조건
시작하기 전에 다음 요구 사항을 충족했는지 확인하십시오.
* JAVA 11버전 이상 [설치](https://www.oracle.com/kr/java/technologies/javase-jdk11-downloads.html)

## Easy Text Editor 사용 방법 

Easy Text Editor를 사용하려면 다음 단계를 따르십시오.

```
> git clone https://github.com/JoongSeokD/easy-text-editor
> cd easy-text-editor
> java -jar -Dfile.encoding=UTF-8 easy-text-editor-1.0.0.jar [임의의 문자열]

예시 )
> 출력: 안녕하세요. 식사는 하셨나요?
> 입력: edit(0,2, “인사”)
> 출력: 인사하세요. 식사는 하셨나요?
> 입력: edit(0,7, “점심 ”)
> 출력: 점심 식사는 하셨나요?
> 입력: undo
> 출력: 인사하세요. 식사는 하셨나요?
> 입력: redo
> 출력: 점심 식사는 하셨나요?
> 입력: undo
> 출력: 인사하세요. 식사는 하셨나요?
> 입력: undo
> 출력: 안녕하세요. 점심 식사는 하셨나요?
> 입력: undo
> 경고: 더 이상 실행취소를 할 수 없습니다.
> 출력: 안녕하세요. 점심 식사는 하셨나요?
> 입력: exit
> 출력: 종료합니다.
```


## Easy Text Editor에 기여하는 방법 
Esay Text Editor에 기여하려면 다음 단계를 따라 주십시오.

1. 이 저장소를 fork 하십시오.
2. branch 만들기 : `git checkout -b <branch_name>`.
3. 변경하고 커밋합니다. `git commit -m '<commit_message>'`
4. 원래 분기로 푸시 : `git push origin <project_name>/<location>`
5. pull request를 요청합니다.

또는 [pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request) 에 대한 GitHub 설명서를 참조하십시오. 


## 연락처

[devmiddlestone@gmail.com](mailto:devmiddlestone@gmail.com)으로 연락주십시오.

## LICENSE

[MIT](https://github.com/all-contributors/all-contributors/blob/master/LICENSE)

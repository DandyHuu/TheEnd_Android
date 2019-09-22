# Android
Đã là git tức là mỗi người tự quản lý quá trình code của mình, xong xuôi thì đẩy lên nhánh chính.

master : nhánh code chính

mỗi ông developer tuỳ theo feature ông làm pull về tự tạo code làm riêng trên máy ông rồi gộp vào.

Fork project trên Github về account trước.
Clone về local git clone http://github.com/myaccount/project-xxx

Tạo branch riêng để phát triển: git checkout -b feature-01

Code loằng ngoằng xong thì commit lên branch riêng đấy: git commit -m "Feature xong het roi."

Gộp vào nhánh source code chính: git checkout master && git merge feature-01

Đẩy code lên account cá nhân: git push origin master

Vô link project gốc thấy cái nút Pull Request , click vào để điền nội dung merge code vào project chính.

Trùm của project chính, thấy có ông nào đó PR lên, vô săm soi xem code có ok không, nếu được thì merge và push lên mainstream.

Hết

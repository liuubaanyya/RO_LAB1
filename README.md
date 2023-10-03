# RO_LAB1
Використати java
а) Тема: Процеси, потоки (нитки).
Завдання: Скласти програму (процес), який паралельно запускає два фонові потоки (нитки). Використати синхронізацію потоків на спільному ресурсі.
Опис роботи програми:
При запуску програми кнопкою "Пуск", одночасно паралельно запускаються два потоку Tthread1 і TThread2, які намагаються встановити "бігунок" в свою позицію (1-в позицію 10, 2-й в позицію 90)
У потоків можна змінювати пріоритети, і в залежності від пріоритету перевага віддається або одному, або іншому потоку
При завершенні програми потоки знищуються

б) Тема: Управління процесами, потоками (нитками) в критичній секції за допомогою блокуючої змінної (найпростішого семафора )
У завданні використовувати програму завдання 1
У програмі зробити наступні зміни:
Ввести глобальну змінну для семафора типу integer
Розмістити кнопки ПУСК 1 і ПУСК 2 для запуску першого і другого потоків (ниток), перед цим встановлюється семафор в положення "зайнято"
Розмістити кнопки СТОП 1 і СТОП 2 для зупинки першого і другого потоків (ниток), встановлюється семафор в положення "вільно"
Кнопка ПУСК 1 задає для першого потоку найнижчий пріоритет
Кнопка ПУСК 2 задає для другого потоку найвищий пріоритет
Опис роботи програми
Потоки запускаються послідовно. Якщо працює один з потоків, то другий неможливо запустити, тому що критична секція зайнята та відображає повідомлення "Зайнято потоком"
Кнопка Стоп звільняє критичну секцію і знищує поточний потік.
Кнопка ПУСК запускає потік і блокує натискання кнопки СТОП іншого потоку
Правильна робота програми полягає в наступному: Кнопка ПУСК 1 встановлює бігунок в положення 10, там він і залишається поки не натискаємо кнопку ПУСК 2, яка встановлює його в стан 90
Звернути увагу! Семафор (блокуюча змінна) - глобальна змінна, доступна обом потокам, отже вони працюють в одному адресному просторі (даного процессу).
Якби семафор регулював взаємодія не потоків, а процесів, то він повинен бути глобальним по відношенню до них і таким чином перебувати в адресному просторі операційної системи, яка і управляє процесами

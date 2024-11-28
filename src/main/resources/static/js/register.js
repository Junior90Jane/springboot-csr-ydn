function showLoginForm(formNumber) {
            // 隱藏所有表單
            document.getElementById('loginForm1').style.display = 'none';
            document.getElementById('loginForm2').style.display = 'none';

            // 根據按鈕選擇顯示相應表單
            if (formNumber === 1) {
                document.getElementById('loginForm1').style.display = 'block';
            } else if (formNumber === 2) {
                document.getElementById('loginForm2').style.display = 'block';
            }
        }

        // 購票會員註冊表單提交事件
        document.getElementById('ticketForm').addEventListener('submit', function(event) {
            event.preventDefault(); // 防止表單的默認提交行為

            const formData = {
                username: document.getElementById('username1').value,
                password: document.getElementById('password1').value,
                gender: document.querySelector('input[name="gender1"]:checked') ? document.querySelector('input[name="gender1"]:checked').value : '',
                ageRange: document.getElementById('age-range1').value,
                email: document.getElementById('email1').value,
                phone: document.getElementById('phone1').value
            };

            // 發送表單數據到後端
            fetch('http://localhost:9090/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                alert('註冊成功');
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('註冊失敗，請稍後再試');
            });
        });
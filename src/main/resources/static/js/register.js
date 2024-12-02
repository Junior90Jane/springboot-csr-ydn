// 獲取 DOM 元素 - 新增購票顧客
const ctAccountInput = document.getElementById('ctAccount');
const ctPasswordInput = document.getElementById('ctPassword');
const gender = document.querySelector('input[name="gender1"]:checked').value.toUpperCase();
const ageInput = document.getElementById('age');
const ctEmailInput = document.getElementById('ctEmail');
const ctPhoneInput = document.getElementById('ctPhone');
// 獲取 DOM 元素 - 新增主辦單位
const ogAccountInput = document.getElementById('ogAccount');
const ogPasswordInput = document.getElementById('ogPassword');
const companyInput = document.getElementById('company');
const ogEmailInput = document.getElementById('ogEmail');
const ogPhoneInput = document.getElementById('ogPhone');

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
document.getElementById('loginForm1').addEventListener('submit', function(event) {
	event.preventDefault(); // 防止表單的默認提交行為

	// 取得輸入資料
	const ctAccount = ctAccountInput.value.trim();
	const ctPassword = ctPasswordInput.value.trim();
	const gender = document.querySelector('input[name="gender1"]:checked').value.toUpperCase().trim();
	const age = ageInput.value.trim();
	const ctEmail = ctEmailInput.value.trim();
	const ctPhone = ctPhoneInput.value.trim();

	// 檢查輸入值
	if (!ctAccount || !ctPassword || !gender) {
		alert('請輸入所有必填欄位！');
		return;
	}

	// 將資料轉為 JSON 格式
	const customer = {
		ctAccount,
		ctPassword,
		gender,
		age,
		ctEmail,
		ctPhone
	};

	// 發送表單數據到後端
	fetch('http://localhost:8989/rest/register/addCustomer', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(customer)
	})
		.then(response => {
			if (!response.ok) {
				throw new Error(`HTTP error! status: ${response.status}`);
			}
			return response.json();
		})
		.then(data => {
			console.log('Success:', data);
			alert('註冊成功');

			// 清空輸入框
			ctAccountInput.value = '';
			ctPasswordInput.value = '';
			ageInput.value = '';
			ctEmailInput.value = '';
			ctPhoneInput.value = '';
			document.querySelector('input[name="gender1"]:checked').checked = false;
		
		// 成功後跳轉到 index.html
		    window.location.href = 'index.html';		})
		.catch((error) => {
			console.error('Error:', error);
			alert('註冊失敗，請稍後再試');
		});
});


// 主辦單位註冊表單提交事件
document.getElementById('loginForm2').addEventListener('submit', function(event) {
	event.preventDefault(); // 防止表單的默認提交行為

	// 取得輸入資料
	const ogAccount = ogAccountInput.value.trim();
	const ogPassword = ogPasswordInput.value.trim();
	const company = companyInput.value.trim();
	const ogEmail = ogEmailInput.value.trim();
	const ogPhone = ogPhoneInput.value.trim();

	// 檢查輸入值
	if (!ogAccount || !ogPassword) {
		addResultText.textContent = '請輸入帳號與密碼！';
		addResultText.style.color = 'red';
		return;
	}

	// 將資料轉為 JSON 格式
	const organizer = {
		ogAccount,
		ogPassword,
		company,
		ogEmail,
		ogPhone,
	};
	
	// 在提交期間禁用按鈕：
	const submitButton = document.querySelector('button[type="submit"]');
	submitButton.disabled = true;


	// 發送表單數據到後端
	fetch('http://localhost:8989/rest/register/addOrganizer', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(organizer)
	})
		.then(response => response.json(),
	 submitButton.disabled = false)
		.then(data => {
			console.log('Success:', data);
			alert('註冊成功');

			// 清空輸入框
			ogAccountInput.value = '';
			ogPasswordInput.value = '';
			companyInput.value = '';
			ogEmailInput.value = '';
			ogPhoneInput.value = '';
			
			// 成功後跳轉到 index.html
			    window.location.href = 'index.html';

		})
		.catch((error) => {
			console.error('Error:', error);
			alert('註冊失敗，請稍後再試');
		});
});

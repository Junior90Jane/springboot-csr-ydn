// 獲取 DOM 元素 - 查詢使用者
const adminList = document.getElementById('adminList');
// 獲取 DOM 元素 - 新增使用者
const adAccountInput = document.getElementById('adAccount');
const adPasswordInput = document.getElementById('adPassword');
const addResultText = document.getElementById('addResult');

// 透過 fetch 經由 http://localhost:8899/rest/admin 取得遠端資料
const fetchAdmins = async () => {
    try {
        const response = await fetch('http://localhost:8899/rest/admin');
        const apiResponse = await response.json();
        if (response.ok) {
            console.log(apiResponse);
            displayAdmins(apiResponse);
        } else {
            console.error('後端返回錯誤:', apiResponse.message);
        }
    } catch (e) {
        console.error('遠端資料存取錯誤', e);
    }
};

// 顯示管理者列表
const displayAdmins = (admins) => {
    adminList.innerHTML = ''; // 清空列表

    admins.forEach((admin) => {
        const listItem = document.createElement('li');
        listItem.textContent = `編號: ${admin.id} 帳號: ${admin.username}`;
        adminList.appendChild(listItem);
    });
};

// 新增管理者
const addAdmin = async () => {
    const adAccount = adAccountInput.value;
    const adPassword = adPasswordInput.value;

    // 檢查資料
    if (!adAccount || !adPassword) {
        addResultText.textContent = '請輸入帳號和密碼';
        return;
    }

    // 遠端新增程序
    try {
        const admin = {
            username: adAccount,
            password: adPassword,
        };

        const response = await fetch('http://localhost:8899/rest/admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(admin),
        });

        const apiResponse = await response.json();

        if (response.ok) {
            // 顯示成功訊息
            alert(`新增成功！ID: ${apiResponse.id}`);
            addResultText.textContent = `新增成功！ID: ${apiResponse.id}`;

            // 更新管理員列表
            fetchAdmins();

            // 清空新增管理員的表單欄位
            adAccountInput.value = '';
            adPasswordInput.value = '';
        } else {
            // 處理後端返回的錯誤訊息
            addResultText.textContent = `錯誤：${apiResponse.message}`;
        }
    } catch (e) {
        console.error('遠端資料存取錯誤:', e);
        addResultText.textContent = '遠端資料存取錯誤，請稍後再試';
    }
};

// 初次載入管理員列表
fetchAdmins();

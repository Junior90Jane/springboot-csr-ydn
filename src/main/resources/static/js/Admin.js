// 獲取 DOM 元素 - 查詢使用者
const adminList = document.getElementById('adminList');
// 獲取 DOM 元素 - 新增使用者
const adIdInput = document.getElementById('adId');
const adAccountInput = document.getElementById('adAccount');
const adPasswordInput = document.getElementById('adPassword');
const addResultText = document.getElementById('addResult');

// 透過 fetch 經由 http://localhost:8899/rest/admin 取得遠端資料
const fetchAdmins = async () => {
    try{
        const response = await fetch('http://localhost:8899/rest/admin');
        const apiResponse = await response.json();
        console.log(apiResponse);
        displayAdmins(apiResponse.data);
    } catch(e){
        console.error('遠端資料存取錯誤', e);
    }
}

// 顯示管理者列表
const displayAdmins = (admins) => {
    adminList.innerHTML = '';

    admins.forEach(admin => {
        const listItem = document.createElement('li');
        listItem.textContent = `編號: ${admin.adId} 帳號: ${admin.adAccount}`;

        // // 刪除按鈕
        // const deleteButton = document.createElement('button');
        // deleteButton.textContent = '刪除';
        // deleteButton.onclick = () => deleteAdmin(admin.adId);
        // listItem.appendChild(deleteButton);
        // 修改按鈕
		
		adminList.appendChild(listItem);
    });
};

// 新增管理者
const addAdmin = async () => {
    const adAccount = adAccountInput.value;
    console.log(adAccount);
    const adPassword = adPasswordInput.value;
    console.log(adPassword);

    // 檢查資料
    if (!adAccount || !adPassword) {
        addResultText.textContent = '請輸入 account 與 password';
        return;
    }

    // 遠端新增程序
    try {
        const admin = {
            adAccount: adAccount,
            adPassword: adPassword
        };

        const response = await fetch('http://localhost:8899/rest/admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(admin)
        });

        // 解析後端回應
        const apiResponse = await response.json();

        if (response.ok) {
            // 顯示成功訊息
            alert(`新增成功！ID: ${apiResponse.adId}`);
            addResultText.textContent = `新增成功！ID: ${apiResponse.adId}`;

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




fetchAdmins();

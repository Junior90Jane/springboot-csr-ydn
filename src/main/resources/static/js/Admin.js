//  獲取 DOM 元素 - 查詢使用者
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
    const adId = adIdInput.value;
    const adAccount = adAccountInput.value;
    const adPassword = adPasswordInput.value;

    // 檢查資料
    if(!adId || !adAccount || !adPassword){
        addResultText.textContent = '請輸入 id, account 與 password';
        return;
    }

    // 遠端新增程序
    try{
        const adminDto = {
            adId: adId,
            adAccount: adAccount
        };

        const response = await fetch('http://localhost:8899/rest/admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(adminDto)
        });

        const apiResponse = await response.json();
		alert(apiResponse.message);
        //addResultText.textContent = apiResponse.message;
		

		if(response.ok) {
            // 重新查詢管理員列表資料
            fetchAdmins(); 
            // 清空新增管理員的表單欄位
            adIdInput.value = '';
            adAccountInput.value = '';
            adPasswordInput.value = '';	
        }
        
    } catch(e) {
        console.error('遠端資料存取錯誤:', e);
    }
}



fetchAdmins();

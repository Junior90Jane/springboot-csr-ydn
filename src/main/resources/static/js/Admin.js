// 獲取 DOM 元素 - 新增使用者
const adIdInput = document.getElementById('adId');
const adAccountInput = document.getElementById('adAccountInput');
const adPasswordInput = document.getElementById('adPasswordInput');

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

// 新增管理者
const addAdmin = async () => {
    const adId = adIdInput.value;
    const adAccount = adAccountInput.value;
    const addPassword = adAccountInput.value;

    // 檢查資料
    if(!adId|adAccount|addPassword){
        addResultText.textContent = '請輸入 id, account 與 password';
        return;
    }

    // 遠端新增程序
    try{
        const adminDto = {
            adId: adId,
            adAccount: adAccount,
            addPassword: addPassword
        };

        const response = await fetch('http://localhost:8899/rest/admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(adminDto)
        });

        const apiResponse = await response.json();
        addResultText.textContent = apiResponse.message;

        // if(response.ok){

        // }
    } catch(e){
        console.log('遠端資料存取錯誤: ', e);
    }
}

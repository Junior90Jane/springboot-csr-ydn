// 獲取 DOM 元素
const adAccountInput = document.getElementById('adAccount');
const adPasswordInput = document.getElementById('adPassword');
const addResultText = document.getElementById('addResult');
const adminModal = new bootstrap.Modal(document.getElementById('exampleModal'), {
    backdrop: 'static', // 背景不可點擊關閉
    keyboard: false // 按鍵不可關閉
});

// 新增管理員函數
async function addAdmin() {
    // 取得輸入資料
    const adAccount = adAccountInput.value.trim();
    const adPassword = adPasswordInput.value.trim();

    // 檢查輸入值
    if (!adAccount || !adPassword) {
        addResultText.textContent = '請輸入帳號與密碼！';
        addResultText.style.color = 'red';
        return;
    }

    // 將資料轉為 JSON 格式
    const adminDto = {
        adAccount,
        adPassword
    };

    try {
        // 發送 POST 請求
        const response = await fetch('http://localhost:8899/rest/admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(adminDto)
        });

        const apiResponse = await response.json();

        // 顯示結果
        if (response.ok) {
            addResultText.textContent = apiResponse.message || '新增成功！';
            addResultText.style.color = 'green';

            // 清空輸入框
            adAccountInput.value = '';
            adPasswordInput.value = '';

            // 關閉 Modal
            setTimeout(() => {
                adminModal.hide();
                addResultText.textContent = '';
            }, 2000);
        } else {
            addResultText.textContent = apiResponse.message || '新增失敗！';
            addResultText.style.color = 'red';
        }
    } catch (error) {
        console.error('錯誤:', error);
        addResultText.textContent = '系統錯誤，請稍後再試。';
        addResultText.style.color = 'red';
    }
}

addAdmin();

let app = new Vue({
    el: '#app',
    data: {
        phone: {
            model: null,
            colors: null,
            storages: null,
            price: null,
            image: null,
        },
        user: {
            name: "",
            phone: "",
        },
        recStyle: 1,
        hideCaptcha: true,
        imageCaptchaSrc: null,
        imageCaptchaValue: null,
        requestAddress: null,
        code: null,
        read: false,
        // 省市县数据
        province: null,
        city: null,
        county: null,
        address: "",
        // 三级联动数据
        provinces: null,
        cities: null,
        counties: null,
        // 通用数据
        session: {
            username: null
        },
        auth: false,
    },
    beforeMount: function () {
        let session = JSON.parse(localStorage.getItem("session"))
        let phone = JSON.parse(localStorage.getItem("phone"))
        if (session == null) location.href = "/login"
        else this.session = session
        if (phone == null)
            location.href = "/home"
        this.phone = phone
    },
    mounted: function () {
        axios.get("/area/1")
            .then(res => {
                if (res.data.status == "success") {
                    this.provinces = res.data.data
                    this.province = 0
                } else {
                    alert("数据错误，请稍后重试")
                    location.reload();
                }
            })
            .catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/home";
                console.error(err);
            })
    },
    methods: {
        getImageCaptcha: function () {
            this.hideCaptcha = false
            axios.get("/captcha/image")
                .then(res => {
                    if (res.data.status == "success") {
                        this.imageCaptchaSrc = "data:image/png;base64," + res.data.data
                    } else {
                        alert("数据错误，请稍后重试")
                        this.hideCaptcha = true
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/order";
                    console.error(err);
                })
        },
        verifyImageCaptcha: function () {
            axios.post("/captcha/image", {
                captchaValue: this.imageCaptchaValue
            }).then(res => {
                if (res.data.status == "success") {
                    this.requestAddress = res.data.data
                    this.getSMSCaptcha()
                    this.hideCaptcha = true
                } else {
                    alert(res.data.message)
                    this.imageCaptchaValue = null
                    this.getImageCaptcha()
                }
            }).catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/order";
                console.error(err);
            })
        },
        getSMSCaptcha: function () {
            axios.get(`/captcha/sms/${this.requestAddress}/${this.user.phone}`)
                .then(res => {
                    if (res.data.status == "success") {
                        this.hideCaptcha = true
                    } else {
                        alert("数据错误，请稍后重试")
                        this.hideCaptcha = true
                    }
                }).catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
        verifySMSCaptcha: function () {
            axios.post("/captcha/sms", {
                phoneNumber: this.user.phone,
                code: this.code
            }).then((res) => {
                if (res.status == "success")
                    this.submit()
                else
                    alert(res.message)
            }).catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/order";
                console.error(err);
            })
        },
        submit: function () {
                axios.post("/orders/", {
                    name: this.user.name,
                    telephone: this.user.phone,
                    price: this.phone.price,
                    address: this.userAddress,
                    userId: this.session.username == null ? null : this.session.id,
                    phoneId: this.phone.id
                }).then(res => {
                    if (res.data.status == "success") {
                        alert("提交成功")
                        location.reload();
                    } else {
                        alert("数据错误，请稍后重试")
                        location.href = "/home";
                    }
                }).catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
        logout: function () {
            axios.post("/logout")
                .then(res => {
                    localStorage.removeItem("session")
                    alert(res.data.data)
                    location.reload()
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
    },
    watch: {
        'user.name': {
            handler: function (newValue, oldValue) {
                this.user.name = newValue.replace(/\s+/g, "")
            },
            deep: true
        },
        'user.phone': {
            handler: function (newValue, oldValue) {
                this.user.phone = newValue.replace(/\s+/g, "")
            },
            deep: true
        },
        address: function (newValue, oldValue) {
            this.address = newValue.replace(/\s+/g, "")
        },
        province: function (newValue, oldValue) {
            this.cities = null
            axios.get("/area/" + this.provinces[newValue].id)
                .then(res => {
                    if (res.data.status == "success") {
                        this.cities = res.data.data
                        this.city = 0
                    } else {
                        alert("数据错误，请稍后重试")
                        location.href = "/home";
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
        city: function (newValue, oldValue) {
            this.counties = null
            axios.get("/area/" + this.cities[newValue].id)
                .then(res => {
                    if (res.data.status == "success") {
                        this.counties = res.data.data
                        this.county = 0
                    } else {
                        alert("数据错误，请稍后重试")
                        location.href = "/home";
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
    },
    computed: {
        userAddress: function () {
            let province = this.provinces == null ? "" : this.provinces[this.province].area
            let city = this.cities == null ? "" : this.cities[this.city].area
            let county = this.counties == null ? "" : this.counties[this.county].area
            return province + city + county + this.address
        },
        disabled: function () {
            return !this.isCorrectName ||
                !this.isCorrectTel ||
                !this.isCorrectAddress ||
                !this.isCorrectCode ||
                !this.read
        },
        isCorrectName: function () {
            return this.user.name != null
        },
        isCorrectTel: function () {
            return /^1([3456789])\d{9}$/.test(this.user.phone)
        },
        isCorrectAddress: function () {
            return this.address != null
        },
        isCorrectCode: function () {
            return this.code != null && this.code.length === 6
        },
        userNameStyle: function () {
            if (this.read && !this.isCorrectTel)
                return 'border: 1px solid red';
        },
        userPhoneStyle: function () {
            if ((this.user.phone.length !== 0 || this.read) && !this.isCorrectTel)
                return 'border: 1px solid red';
        },
        addressStyle: function () {
            if (this.read && !this.isCorrectAddress)
                return 'border: 1px solid red';
        },
        hiddenCaptcha: function () {
            if (this.hideCaptcha)
                return 'display: none';
        }
    }
})

let app = new Vue({
    el: '#app',
    data: {
        colorOption: null,
        storageOption: null,
        buyingOption: null,
        bodyOption: null,
        screenOption: null,
        displayOption: null,
        serviceOption: null,
        IDOption: null,
        featureOption: [],
        model: null,
        colors: null,
        storages: null,
        price: null,
        image: null,
        auth: false
    },
    beforeMount: function () {
        let phoneId = window.location.href.split("/").pop()
        axios.get("/phone/" + phoneId)
            .then(res => {
                this.model = res.data.data.model
                this.colors = res.data.data.color.split("-")
                this.storages = res.data.data.storage.split("-")
                this.price = res.data.data.price
                this.image = res.data.data.image
            })
            .catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/home";
                console.error(err);
            })
    },
    methods: {
        valuation: function () {
            alert("您的最终估价为 " + this.finalPrice + " 元")

        },
        logout: function () {
            axios.post("/logout")
                .then(res => {
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
    watch: {},
    computed: {
        canValuation: function () {
            return this.colorOption != null &&
                this.storageOption != null &&
                this.buyingOption != null &&
                this.bodyOption != null &&
                this.screenOption != null &&
                this.displayOption != null &&
                this.serviceOption != null &&
                this.IDOption != null
        },
        discount: function () {
            return this.storageOption +
                this.bodyOption +
                this.screenOption +
                this.displayOption +
                this.serviceOption +
                this.featureOption.length +
                (this.IDOption * 100);
        },
        finalPrice: function () {
            if (this.discount >= 20)
                return 0
            else
                return Math.floor(this.price * (20 - this.discount) * 0.05)
        }
    }
});
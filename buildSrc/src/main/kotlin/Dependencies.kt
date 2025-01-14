object Dependencies {
    val androidCore by lazy { "androidx.core:core-ktx:${Versions.androidCore}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val archCore by lazy { "androidx.arch.core:core-testing:${Versions.archCore}" }
    val coilCompose by lazy { "io.coil-kt:coil-compose:${Versions.coilCompose}" }
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}" }
    val daggerHilt by lazy { "com.google.dagger:hilt-android:${Versions.daggerHilt}" }
    val daggerHiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}" }
    val daggerHiltNavCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.daggerHiltNavCompose}" }
    val epoxy by lazy { "com.airbnb.android:epoxy:${Versions.epoxy}" }
    val epoxyDatabinding by lazy { "com.airbnb.android:epoxy-databinding:${Versions.epoxy}" }
    val epoxyProcessor by lazy { "com.airbnb.android:epoxy-processor:${Versions.epoxy}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val javaxInject by lazy { "javax.inject:javax.inject:${Versions.javaxInject}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val junitTest by lazy { "androidx.test.ext:junit:${Versions.junitTest}" }
    val lifecycleLivedata by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
    val lifecycleViewmodel by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val materialCompose by lazy { "androidx.compose.material:material:${Versions.materialCompose}" }
    val mockitoAndroid by lazy { "org.mockito:mockito-android:${Versions.mockito}" }
    val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockito}" }
    val mockitoInline by lazy { "org.mockito:mockito-inline:${Versions.mockitoInline}" }
    val moshiAdapters by lazy { "com.squareup.moshi:moshi-adapters:${Versions.moshi}" }
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }
    val navigation by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val okhttpInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}" }
    val room by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val splashScreen by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen}" }
    val swipeRefreshLayout by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val toolingCompose by lazy { "androidx.compose.ui:ui-tooling:${Versions.toolingCompose}" }
}

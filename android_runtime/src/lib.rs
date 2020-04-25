extern crate jni;

use jni::{
    objects::{GlobalRef, JClass, JObject, JValue},
    sys::jbyteArray,
    JNIEnv, JavaVM,
};

#[no_mangle]
pub unsafe extern "C" fn Java_com_wasmer_android_MainActivity_JNIExecuteWasm(
    env: JNIEnv,
    _: JClass,
    callback: JObject,
) {
    loop {
        call_test(&env, &callback, "test");
    }
}

pub fn call_test(env: &JNIEnv, class: &JObject, test: &str) {
    let test = env
        .new_string(test)
        .expect("Couldn't create a java string!");
    let test = JValue::Object(test.into());
    env.call_method(*class, "Test", "(Ljava/lang/String;)V", &[test]).unwrap();
}
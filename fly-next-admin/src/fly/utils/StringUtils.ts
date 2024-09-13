/**
 * 字符串工具类
 */
export class StringUtils {

    /**
     * 判断字符串是否为空
     * @param value 要判断的字符串
     */
    static isEmpty(value: string | null | undefined): boolean {
        return (null === value || undefined === value || '' === value.trim() ||
            'null' === value || 'NULL' === value || 'undefined' === value ||
            'UNDEFINED' === value || ' ' === value);
    }

    /**
     * 判断字符串是否非空
     * @param value 要判断的字符串
     */
    static isNotEmpty(value: string | undefined): boolean {
        return !StringUtils.isEmpty(value);
    }

    /**
     * 判断字符串是否为空或仅包含空白字符
     * @param value 要判断的字符串
     */
    static isBlank(value: string): boolean {
        return /^\s*$/.test(value);
    }

    /**
     * 判断字符串是否非空且不仅包含空白字符
     * @param value 要判断的字符串
     */
    static isNotBlank(value: string): boolean {
        return !StringUtils.isBlank(value);
    }

    /**
     * 去除字符串首尾的空白字符
     * @param value 要处理的字符串
     */
    static trim(value: string): string {
        if (null === value) {
            return '';
        }
        return value.trim();
    }

    /**
     * 判断两个字符串是否相等，忽略大小写
     * @param str1 字符串1
     * @param str2 字符串2
     */
    static equalsIgnoreCase(str1: string, str2: string): boolean {
        return str1.toLowerCase() === str2.toLowerCase();
    }

    static split(value: string, split: string): string[] {
        if (this.isEmpty(value)) {
            return [];
        }
        return value.split(split);
    }

}

// 使用示例
// const str = "  Hello World!  ";
// console.log(StringUtils.isEmpty(str)); // false
// console.log(StringUtils.isNotEmpty(str)); // true
// console.log(StringUtils.isBlank(str)); // false
// console.log(StringUtils.isNotBlank(str)); // true
// console.log(StringUtils.trim(str)); // "Hello World!"
// console.log(StringUtils.equalsIgnoreCase("hello", "HELLO")); // true
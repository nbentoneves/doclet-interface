# Sesame Java

### Status
[![Coverage Status](https://coveralls.io/repos/github/easyJsonApi/easyJsonApi/badge.svg?branch=master)](https://coveralls.io/github/easyJsonApi/easyJsonApi?branch=master)
[![Build Status](https://travis-ci.org/nbentoneves/easyJsonApi.svg?branch=master)](https://travis-ci.org/easyJsonApi/easyJsonApi)


## Examples:
 
    [
        //First parameter
        {
            "isPrimitive": true,
            "value": "2"
        },
        //Second parameter
        {
            "isPrimitive": true,
            "value": "3"
        },
        //Three parameter
        {
            "isPrimitive": false
        }
    ]

### Notes

        try {

            Class<?> clazz = Class.forName("com.ppb.platform.TestDomain");
            Object object = clazz.newInstance();
            Field field = clazz.getDeclaredField("test");

            field.setAccessible(true);
            field.set(object, "ola");

            LOGGER.info(object.toString());

            Class<?> clazzDomainObject = Class.forName("com.ppb.platform.TestDomainObject");
            object = clazz.newInstance();
            field = clazz.getDeclaredField("test");

            field.setAccessible(true);
            field.set(object, "ola");

            LOGGER.info(object.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

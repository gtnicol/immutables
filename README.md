Read full documentation at http://immutables.org

[![Build Status](https://travis-ci.org/immutables/immutables.svg?branch=master)](https://travis-ci.org/immutables/immutables)

```java
// Define abstract value type using interface, abstract class or annotation
@Value.Immutable
public interface ValueObject extend WithValueObject {
  // extend not-yet-generated WithValueObject to inherit `with*` method signatures
  String getName();
  List<Integer> getCounts();
  Optional<String> getDescription();

  class Builder extends ImmutableValueObject.Builder {}
  // ImmutableValueObject.Builder will be generated and
  // our builder will inherit and reexport methods as it's own
}

// Use generated immutable implementation and builder
ValueObject v =
    new ValueObject.Builder()
        .name("Nameless")
        .description("present")
        .addCounts(1)
        .addCounts(2)
        .build();

v = v.withName("Doe");
```

License
---------

```
   Copyright 2013-2018 Immutables Authors and Contributors

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

Changelog
---------

### 2.7.4 (2018-12-26)
- Bugfix and refinements release! Thank you issue reports and PRs!
- Notable stuff
  + Propagate checked exceptions in `throws` for lazy attributes (only for `@Value.Lazy`)
- [Issues 2.7.4](https://github.com/immutables/immutables/milestone/73?closed=1)

### 2.7.3 (2018-11-10)
- Important fix for Modifiables/Immutables/from (aka mergeFrom) methods broken in 2.7.2
- [Issues #863 #864](https://github.com/immutables/immutables/milestone/72?closed=1)

### 2.7.2 (2018-11-05)
- Many fixes and corrections! Thank you issue reports and PRs! 
- [Issues 2.7.2](https://github.com/immutables/immutables/milestone/71?closed=1)
- Notable stuff
  + Experimental support for Codecs and Jackson (along with Gson) for mapping to MongoDB repositories (#850, #817 by @asereda-gs)
  + `Style.optionalAcceptNullable=true` now also generates alternative constructor accepting nullable input for `java.util.Optional` attributes (#843 by @ThLeu)
  + `org.immutables.value.Generated` now covers generated nested classes (#854 by @before)

### 2.7.1 (2018-08-25)
- Thank you issue reports and PRs! Not many features here, but minor version increment was needed
- Notable stuff
  + Added `org.immutables:value-annotations` annotation only jar. Previously we had equivalent artifact `org.immutables:value:annotations` (classifier `annotations`). Apparently some tools have trouble with properly addressing jars with classifiers and attached sources. Both artifacts are released.
  + `org.immutables.value.Generated` annotation (which auto added to generated classes / builders) is class-retention annotation now. Some tools (like osgi related) will not automatically add such annotation as runtime dependency.
  + `Automatic-Module-Name` now have no dashes for `org.immutables.value.annotations` (annotation only) and `org.immutables.value.processor` (unshaded processor). Most used artifact/module `org.immutables.value` is obviously unchanged.
  + Can suppress a bit finer categories of warnings `@Suppression("immutables:subtype")`, each warning message now contain such token in parentheses.
  + Subtyping value type with another value type directly is now a warning, not an error. It's still better avoid it.
- [Issues 2.7.1](https://github.com/immutables/immutables/milestone/70?closed=1)

### 2.6.3 (2018-07-21)
* https://github.com/immutables/immutables/issues/784
* https://github.com/immutables/immutables/issues/788

### 2.6.1 (2018-05-23)
- Hotfixes for 2.6. Thank you for reported issues and PRs!
- [Issues 2.6.1](https://github.com/immutables/immutables/milestone/69?closed=1)

### 2.6.0 (2018-05-19)
- Tons of fixes and small improvements. Thank you for reported issues and PRs!
- [Issues 2.6.0](https://github.com/immutables/immutables/milestone/68?closed=1)
- Notables features
  + Fixed long standing issue with compile error when using inverse generation style and using attributes with not-yet-generated generic types (the issue with `<any>`)
  + Experimental annotation injection: allows injecting annotation on fields, accessors, initializer, immutable and builder types etc. Injection directives are defined as custom annotations that are meta-annotated with `@InjectAnnotation` in new `org.immutables:annotate` module. Allow some non-trivial annotation code construction, see javadoc of `@InjectAnnotation` for details.
  + `canBuild` method can be generated on builders which checks if all mandatory attributes are set. It's not generated by default, but once its name is specified in style naming template it will be generated on builder (`Style(canBuild="canBuild")`)
  + `Style.throwForNullPointer` can be used to customize exception thrown on routine null checks.
  + `Style.allowedClasspathAnnotations` whitelist for allowed annotations which automaticllay used to decorate generated classed when available via classpath lookup. Includes some of `javax.annotation.*`, _ErrorProne_, _FindBugs_ etc.
  + `Style.weakInterning` style can be used to enable _weak_ interning when enabled via `@Value.Immutable(intern = true)`
  + Introduced own class and runtime retained annotation `org.immutables.value.Generated` to be used for bytecode-inspecting and reflection-based tools to include/exclude generated files.
  + `@Gson.Other JsonObject` can collect other/unknown fields in objects mapped with _Gson_ TypeAdapters
  + Avoid using `Lists.newArrayList()` in favor of plain `new ArrayList<>()` when _Guava_ is present

### 2.5.0 (2017-05-14)
- Number of bugfixes including _Jackson_ and _Gson_ fixes. Thank you for the PRs!
- Notable features
  + `@Value.Redacted` annotation with `Style.redactedMask` customization setting.
- [Issues 2.5.0](https://github.com/immutables/immutables/milestone/61?closed=1)

### 2.4.x-2.4.4 (2017-03-12)
- Enhancement and bugfix release. Thanks to issue reporters and contributors! PRs were great!
- Notable features
  + Fancier hash code generation.
  + if `hashCode` is precomputed (`prehash=true`), it is used in `equals` to short circuit unequal object comparison.
  + `@NaturalOrder/ReverseOrder` now works with `SortedMultiset`
  + `allMandatoryParameters = true` style to automatically turn all mandatory attributes into parameters
  + `builtinContainerAttributes = false` to disable recognition and special handling of built-in collection type. Custom encodings would still work.
  + `@Builder.AccessibleFields` to generate builder with protected fields. Override/access using nested Builder class extending auto-generated one.
  + Gson/Jackson refinements and fixes
  + new `META-INF/extensions/org.immutables.inhibit-classpath` file to specify a blacklist of classnames(or prefixes) to hide them from lookup: useful if you want to disable features or integrations that are otherwise auto-enabled when certain classes are found in classpath. (But please, do not package it in libs you would distribute to 3rd parties)
- [Issues 2.4.0](https://github.com/immutables/immutables/issues?q=milestone%3A2.4.0)
- [Issues 2.4.1](https://github.com/immutables/immutables/issues?q=milestone%3A2.4.1)
- [Issues 2.4.2-2.4.3](https://github.com/immutables/immutables/issues?q=milestone%3A2.4.3)
- [Issues 2.4.4](https://github.com/immutables/immutables/issues?q=milestone%3A2.4.4)

### 2.3.7 (2016-10-19)
- Enhancement and bugfix release. Thanks to issue reporters!
  + `@AllowNulls and @SkipNulls` BYOA annotations supported for Collections and Maps when `@Style(jdkOnly = true)`
  + `@JsonDeserialize(builder = ImmutableVal.Builder.class)` is now supported when using builder with constructor `@Value.Style(builder = "new")` or `class Builder extends ImmutableVal.Builder`
  + If `Style.forceJacksonIgnoreFields = true`, generate `@JsonIgnore` on fields to avoid potential conflicts with attributes annotated with `@JsonProperty`
  + Disable automatic Jackson integration via `@Style(jacksonIntegration = false)` for fully custom mappings using `@JsonSerialize/@JsonDeserialize` annotations
  + Staged/Telescopic builder is generated when `@Style(stagedBuilder = true)`
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.3.7)

### 2.3.2 (2016-09-27)
- Really minor change and bugfix release. Thanks to issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.3.2)

### 2.3 (2016-09-06)
- Minor change and bugfix release. Thanks to issue reporters!
- Things to be aware of
  + Generated naming changed when inferred attribute name clashes with keyword. Previously for `isDefault` accessor, corresponding initializer `setIsDefault` would be generated for `"set*"` pattern. Now it `setDefault` will be the name of generated method. JSON name `default` would be also derived.
  + `org.immutables:trees` module is renamed from the old `cases` module
  + While still experimental, encodings are taking more shape now. `@Encoding.Init` on value replaced with separate `@Encoding.Of` annotation to better express purpose, for builders `@Encoding.Init` serve the same role as before.  
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.3)

### 2.2.12 (2016-08-18)
- Bugfixes! Thanks to issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.12)

### 2.2.11 (2016-08-16)
- Bugfixes! Thanks to issue reporters!
- Early preview of "encodings" functionality to support custom collections and other custom types/containers!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.11)

### 2.2.10 (2016-07-03)
- Hotfix release. Thanks to issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.10)

### 2.2.9 (2016-07-01)
- Thanks to issue reporters and contributors!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.9)

### 2.2.7 (2016-06-19)
- Thanks to issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.7)

### 2.2.7 (2016-06-19)
- Thanks to issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.7)

### 2.2.5 (2016-06-09)
- Thanks to contributors and issue reporters!
- Bugfix and minor enhancement release
  + Numerous fixes for generics and interactions with other features
  + SkipNulls and Nullable elements in maps and collections if you really need them (#349)
  + `With*` interface generation to finely hide implementation classes while still having `with*` methods in API
  + Straw man support for custom collections (see [/META-INF/extensions/org.immutables.collect](https://github.com/immutables/immutables/blob/master/value-fixture/src/META-INF/extensions/org.immutables.collect))
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.5)

### 2.2.2 (2016-06-03)
- Thanks to contributors and issue reporters!
- Bugfix release
  + Running in Eclipse Neon RC due to APT classloading changes
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.2)

### 2.2.1 (2016-05-27)
- Thanks to contributors and issue reporters!
- Bugfixes and functionality refinements
  + Can disable generation of META-INF/services for Gson adapters `Gson.TypeAdapters.metainfServices = false`
  + Gson Adapters may be configured to skip nulls in favor of default values. `Gson.TypeAdapters.nullAsDefault = true`
  + Numerous bug fixes
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2.1)

### 2.2 (2016-05-16)
- Thanks to contributors and issue reporters!
- Improvements
  + Generic parameters support for Immutable objects! Modifiables are supported (likely).
  + Jackson/Gson Adapters support for generics
  + Collections now can be nullable and/or also `@Value.Default`. Default attributes can be nullable, yet allowing arbitrary default values.
  + Depluralization dictionaries can be specified on multiple levels (type, package, meta-annotation). All applicable dictionary entries will be merged.
- Backward compatibility should be preserved.
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.2)

### 2.1.19 (2016-05-04)
- Minor Hotfix, Thank you!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.19)

### 2.1.18 (2016-04-26)
- Minor Bugfixes, Thank you!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.18)

### 2.1.16 (2016-04-14)
- Thanks to contributors and issue reporters, important bugfixes were made!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.16)
- Minor enhancements
  + Javaslang Option support
  + More compact toString, omitting non-present Optionals and nulls
  + Cancel-out individual parameters when `allParameters = true`

### 2.1.14 (2016-03-12)
- Bugfix and minor improvement release.
  + Output package pattern can be configured using style
  + Ad-Hoc normalization using Value.Check
  + `Style(deepImmutablesDetection = true)` behavior refined
  + Refined `JsonSerialize` enclosing top-level type annotation handling
- Thanks to contributors and issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.14)

### 2.1.12 (2016-02-29)
- Bugfix and minor changes release.
  + Findbugs' `SuppressFBWarnings` placed on immutable implementation if found in the classpath
  + Primitive wrapper types and strings have an `equals` comparison now in `with*` methods in short-circuiting check to `return this`.
  + Fixed some Eclipse specific compilation bugs.
- Thanks to contributors and issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.12)

### 2.1.11 (2016-02-18)
- Bugfix and Minor improvement release:
  + Depluralization for `add*` and `put*` methods enabled with `Style(depluralize, depluralizeDictionary)`
  + Fixed regression while using `java.util.Optional*` with `Builder.Factory` caused by auto-unboxing to nullable fields in 2.1.9
  + Improved handling for some internal problems in the processor
- Thanks to contributors and issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.11)

### 2.1.10 (2016-02-02)
- Bugfix release:
  + License headers, added missing, plus some cleanup
  + Fixed override of final method in some cases
  + Javadoc linting to fix javadocs in generated code
- Thanks to contributors and issue reporters!  
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.10)

### 2.1.9 (2016-01-26)
- Bugfix and Minor improvement release:
  + Automatic unboxing and boxing of java.util.Optional<A> to nullable A fields
- Thanks to contributors and issue reporters!  
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.9)

### 2.1.8 (2016-01-15)
- Bugfix and Minor improvement release:
  + New 'func' module: Functions and predicate generator (for Guava, pre java 8)
  + `@Builder.Parameter` and `@Builder.Switch` are working on value attributes now
  + New 'android-stub' module, may be useful to compile android libraries using Immutables for API level < 19
- Thanks to contributors and issue reporters!  
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.8)

### 2.1.5 (2016-01-02)
- Bugfix and Minor improvement release.
- Thanks to contributors and issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.5)
  + Public constructors style

### 2.1.4 (2015-12-10)
- Bugfix and Minor improvement release.
- Thanks to contributors and issue reporters!
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.4+is%3Aclosed)
  + Gson 2.5 support for SerializedName annotation including alternate names

### 2.1.1 (2015-12-03)
- Bugfix and Minor improvement release.
- Many thanks to all user and contributors for issue reports, PRs and suggestions!
  + Notable JSON fixes. Courtesy of @ldriscoll
  + Generated Javadocs corrections and proofreading. Courtesy of @io7m and @trask
  + Atlassian Fugue 2.x and 3.x `Option` support. Courtesy of @mzeijen
- [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1.1+is%3Aclosed)

### 2.1.0 (2015-10-23)
+ Added `Value.Modifiable` annotation to create modifiable companion classes, which may serve as uber-builder or instead of `buildPartial` in other builder toolkits.
+ Added number of minor styles and feature flags and refinements of existing functionality
+ Numerous bugfixes
+ [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.1+is%3Aclosed)

### 2.0.18 (2015-08-13)
+ Bugfix and minor enhancement release [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.0.18+is%3Aclosed)

### 2.0.17 (2015-08-06)
+ Bugfix and minor enhancement release [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.0.17+is%3Aclosed)

### 2.0.16 (2015-07-09)
+ Bugfix release [Issues](https://github.com/immutables/immutables/issues?q=milestone%3A2.0.16+is%3Aclosed)

### 2.0.15 (2015-07-02)
+ Bugfixes and minor improvements [Issues](https://github.com/immutables/immutables/issues?q=is%3Aissue+is%3Aclosed+milestone%3A2.0.15)

### 2.0.14 (2015-06-18)
+ Bugfixes and minor improvements [Issues](https://github.com/immutables/immutables/issues?q=is%3Aissue+is%3Aclosed+milestone%3A2.0.14)

### 2.0.13 (2015-06-14)

+ Added new experimental [serialization module](http://immutables.github.io/immutable.html#serialization) with advanced structural binary serialization, which is based on the standard java binary serialization that allows for object evolution to some degree.
+ Bugfixes along with minor refinements of annotation handling. [Issues](https://github.com/immutables/immutables/issues?q=is%3Aissue+is%3Aclosed+milestone%3A2.0.13)

### 2.0.10 (2015-04-28)
Bugfix release along with other 2.0.X. [Issues](https://github.com/immutables/immutables/issues?q=is%3Aissue+is%3Aclosed+milestone%3A2.0.10)

### 2.0 (2015-03-24)
Many thanks to all contributors who helped to make it happen.
Thanks to the community for making feature requests, bug reports, questions and suggestions.

_Note versions 1.1.x are still supported, there's no rush to switch to 2.0 if you are not ready._

+ Thanks to @augustotravillio for implementing JDK-only code generation. Useful on Android or when Guava is not available.
+ Thanks to @ivysharev for a lot more precise imports post-processor.

#### Features
+ Support for java 8, including new `Optional*` classes, default methods. But type annotation support is rudimentary (works only in some cases). Java 7 is still required for compilation
+ `Multiset`, `Multimap`, `SetMultimap`, `ListMultimap` are now supported.
+ Full-featured Gson support with generated `TypeAdapter`s which use no reflection during serialization/deserialization.
+ Builder now can be generated as "strict" (Style#strictBuilder). Strict builders prevents initialization errors: addition only collection initializer and regular initializers could be called only once.
+ Now, there's no required dependencies, plain JDK will suffice. Guava still has first class support.
+ Processor now enjoy improved repackaging (using forked and patched `maven-shade-plugin`)
+ Added `@Builder.Switch` annotation
+ Numerous API and behavior refinements, resulting in lot less WTF.

#### Changes
+ Main annotation and processor artifact changed to be `org.immutables:value`. There's no confusing `value-standalone` or whatsoever.
+ `common` artifact was removed, all compile and runtime dependencies have been modularized. While annotation processor itself is pretty monolithic, now compile and optional runtime dependencies are externalized to dedicated artifacts. Some notable modules:
  * `gson` Gson support module
  * `mongo` MongoDB support module
  * `builder` Module with annotations for generating builder from static factory methods
  * `ordinal` Module to generate more exotic enum-like values and efficiently handle them, etc
+ JSON infrastructure underwent overhaul. See guide at http://immutables.org/json.html
+ JAX-RS support switched to Gson, for _Jackson_ integration there's no need to integrate anything, its own provider will fully work.
+ MongoDB repository generation was refined and adjusted following JSON changes. See guide at http://immutables.org/mongo.html
+ Temporarily removed JDBI integration. It may be resurrected later.
+ Direct inheritance of `@Value.Immutable` from another `@Value.Immutable` is discouraged.
+ Limited (and constrained to same level) inheritance of `@Value.Parameter` attributes.
+ Builder now has method to set/reset collection content (in non-strict mode)
+ Package style now also applies to all classes in sub-packages unless overridden
+ Constructor parameters for collections now accept more liberal input. `List<T>` parameter accepts `Iterable<? extends T>` etc.
+ Removed sample style annotation like `@BeanStyle.Accessors` in favor of documentation and samples
+ `@Value.Nested` was renamed to `@Value.Enclosing`
+ `@Value.Immutable#visibility` was moved to style `@Value.Style#visibility`
+ `@Value.Immutable.Include` was moved to `@Value.Include`
+ Moved `@Value.Builder` to `builder` module where it is called `@Builder.Factory`. Added `@Builder.Parameter` and `@Builder.Switch` to fine-tune generation of builders for factory methods.

### 1.1 (2014-12-27)

#### Features
+ [#53](https://github.com/immutables/immutables/issues/53) Implemented `SortedSet`/`NavigableSet`/`SortedMap`/`NavigableMap` attributes specifying `@Value.NaturalOrder` or `@Value.ReverseOrder` annotation. Idea contributed by Facebook Buck team. Thanks!
+ [#63](https://github.com/immutables/immutables/issues/63)  `@Value.Builder`: implemented standalone builder generations for static factory methods. This makes it possible to create builders for arbitrary factory methods, including google/AutoValue _create_ methods!
+ [#38](https://github.com/immutables/immutables/issues/38) `@Value.Immutable.Include`: Ability to include other classes even from different packages and libraries as abstract value types. Think of generating immutable implementation of annotations from different library!
+ [#33](https://github.com/immutables/immutables/issues/33) `@Value.Style`: insanely flexible style customization infrastructure now allows to tailor generated immutable types and builders to wide range of style and preferences!
  + `@BeanStyle.Accessors` is example of style annotations - allows accessors to be detected from with 'get' and 'is' prefixes, so prefix will be stripped on builder and in toString.
+ [#35](https://github.com/immutables/immutables/issues/35) `@Nullable` attributes. Support any annotation named `Nullable`. Thanks to John Wood for this and other valuable feature and bug reports!
+ [#44](https://github.com/immutables/immutables/issues/44) Ability to run generated classes on JDK6 (including runtime support library `common`). JDK7 is still required to run annotation processor. Credits to Trask Stalnaker for the contribution!
+ Improved code generation: more clean code, more useful javadocs, dozens of fixes to edge cases, more correctness for customized value types.
+ [#64](https://github.com/immutables/immutables/issues/64) `org.immutables.json-runtime` minimal JSON runtime jar, extracted from `common` with only necessary transitive Jackson dependencies.
+ [#54](https://github.com/immutables/immutables/issues/54) Support for including Jackson marshaled POJOs as attributes of `@Json.Marshaled` immutable objects. Together with `@Jackson.Mapped` this provides round-tripping from _Immutables'_ marshalers to Jackson and back.

#### Changes
* Dozens of fixes, including
  - [#61](https://github.com/immutables/immutables/issues/61) Partially fixed `@Value.Default` methods on Java 8 implemented with interface `default` methods. Known issue is with more complex interface inheritance [#67](https://github.com/immutables/immutables/issues/67)
  - [#48](https://github.com/immutables/immutables/issues/48) JDBI marshaling fixes
  - [#50](https://github.com/immutables/immutables/issues/50) Support for older versions of Guava, which did not have `MoreObjects` for example, detected from classpath. Checked with Guava v12, v16
  - Fixed resolution of accesors inherited from couple of interfaces. (Still do not take into account most specific covariant override)
* Deprecations
  - Deprecated `@Value.Immutable(nonpublic)` in favor of `@Value.Immutable(visibility)`, nonpublic not working now, but it should not break
  - Deprecated `@Value.Immutable(withers)` in favor of `@Value.Immutable(copy)`
  - Deprecated `@Value.Getters` in favor of using `@Value.Style`. May be undeprecated if found really useful
  - Removed underdeveloped annotations and processors to be reintroduced later (Transformer, Visitor, Parboiled)
* Incompatibilites
  - Upgrade to Jackson 2.4.4 for Jackson `ObjectMapper` cross-marshaling to work
  - Possible incompatibity: `@Json.Marshaled` now is required on each nested `@Value.Immutable`, marshaled annotation on `@Value.Nested` will not have effect
  - [#59](https://github.com/immutables/immutables/issues/59) `@Value.Default` on collection attributes now issues warning, that makes collection attribute generated as plain regular attributes without any special collection support in builder

### 1.0.1

#### Fixes
+ Improper unchecked suppressions in generated files [#36](https://github.com/immutables/immutables/issues/36)
+ fixed/refined underwriting of methods: hashCode, equals, toString [#37](https://github.com/immutables/immutables/issues/37)
+ Fixed duplication of instanceof checks in Transfromers
+ Fixed implementation of nDeprecationsonpublic=true (package private) immutable classes

#### Changes
+ Internal: using released 1.0 ‘value-standalone’ for self-compiling, rather than 'retrovalue' system/jar
+ Internal: made marshaling binding problems IOException instead of runtime

### 1.0
Release with all of what was developed, including reengineering of template engine, project/module restructuring and annotation API changes

#### Changes
* Immutable generation annotation now nested below umbrella annotation `@org.immutables.value.Value` which provided grouping and namespacing for the nested annotations.
  - `@GenerateImmutable` is now `@Value.Immutable`
  - `@GenerateConstructorParameter` is now `@Value.Parameter`
  - ... and so on, see website and API documentation for the details
* See other umbrella annotations in `org.immutables.value.*` package: `@Json`, `@Mongo`, `@Jackson`
* Main standalone artifact for the annotation processor is now `org.immutables:value-standalone:1.0`. There's is quick start module with transitive dependencies for integrations — to not pick dependencies one by one — `org.immutables:quickstart:1.0`
* Most notable generated API changes
  + Added `ImmutableValue.copyOf` methods
  + Added array attributes
  + Added `Builder.addAttribute(T...)` overload for collection attributes
  + Removed `ImmutableValue.Builder.copy` methods
